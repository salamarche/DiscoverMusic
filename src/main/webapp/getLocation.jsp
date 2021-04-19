<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 4/15/2021
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Location</title>
</head>
<body>

    <form action="discoverAction" method="post" id="locationForm">
        <label for="country">Select a Country</label>
        <input list="countryList" name="country" id="country">
        <datalist id="countryList">
        </datalist>

        <label for="region">Select a State/ Providence/ Region</label>
        <input list="regionList" name="region" id="region">
        <datalist id="regionList">
        </datalist>

        <label for="city">Select a City</label>
        <input list="cityList" name="city" id="city">
        <datalist id="cityList">
        </datalist>

        <input type="hidden" id="selectedCountryId" name="selectedCountryId" value="">
        <input type="hidden" id="selectedRegionId" name="selectedRegionId" value="">
        <input type="hidden" id="selectedCityId" name="selectedCityId" value="">

        <input type="submit" value="enter location">
    </form>

    <script>

        let countryUrl = "./services/countries";

        const countryList = document.querySelector("#countryList");
        const regionList = document.querySelector("#regionList");
        const cityList = document.querySelector("#cityList");

        const countryInput = document.querySelector("#country");
        const regionInput = document.querySelector("#region");
        const cityInput = document.querySelector("#city");

        const countryIdInput = document.querySelector("#selectedCountryId");
        const regionIdInput = document.querySelector("#selectedRegionId");
        const cityIdInput = document.querySelector("#selectedCityId");

        const getRequestJSON = (url, callBack) => {
            let xhr = new XMLHttpRequest();
            xhr.open("get", url);
            xhr.responseType = "json";
            xhr.onreadystatechange = () => {

                if (xhr.readyState === 4) {
                    callBack(xhr.response);
                }
            }
            xhr.send(null);
        }

        const findOptionId = (datalist, nameValue ) => {
            let options = datalist.querySelectorAll("option");
            let thisId;
            for (i = 0; i < options.length; i++ ) {
                let option = options[i]
                let name = option.value;

                if (name == nameValue) {
                    thisId = option.getAttribute("id");
                    break;
                }
            }

            return thisId;
        }

        const getData = () => {
            return new Promise((resolve, reject) => {
                getRequestJSON(countryUrl, (response) => {
                    if (response == "") {
                        let error="There was an http issue";
                        reject(error);
                    } else {
                        let data = response;
                    resolve(data)
                    }
                });
            });
        }

        const populateCountryData = (data) => {
            for (i = 0; i < data.length; i++ ) {
                let country = data[i];
                let countryName = country.countryName;
                let countryId = country.id;

                let option = document.createElement("option");
                option.setAttribute("value", countryName);
                option.setAttribute("id", countryId);

                countryList.appendChild(option);

            }
        }

        const populateRegionData = (data) => {
            //clear existing values for region and city
            let cityOptions = cityList.querySelectorAll("option");
            let regionOptions = regionList.querySelectorAll("option");
            cityOptions.forEach(o => o.remove());
            regionOptions.forEach(o => o.remove());
            cityInput.value = "";
            regionInput.value = "";
            cityIdInput.setAttribute("value", "");
            regionIdInput.setAttribute("value", "");

            //get countryId and set hidden input value
            let selectedValue = countryInput.value;
            let countryId = findOptionId(countryList, selectedValue);
            countryIdInput.value = countryId;

            let country = data.filter(c => c.id == countryId);
            let regions = country[0].regions;

            for (i = 0; i < regions.length; i++) {
                let region = regions[i];

                let regionName = region.regionName;
                let regionId = region.id;

                let option = document.createElement("option");
                option.setAttribute("value", regionName);
                option.setAttribute("id", regionId);
                regionList.appendChild(option);
            }
        }

        const populateCityData = (data) => {
            //clear existing values for city
            let cityOptions = cityList.querySelectorAll("option");
            cityOptions.forEach(o => o.remove());
            cityInput.value = "";
            cityIdInput.setAttribute("value", "");

            //get regionId and countryId, set regionId
            let selectedValue = regionInput.value;
            let regionId = findOptionId(regionList, selectedValue);
            regionIdInput.value = regionId;
            let countryId = countryIdInput.value;

            //populate city datalist
            let country = data.filter(c => c.id == countryId);
            let regions = country[0].regions;
            let region = regions.filter(r => r.id == regionId);
            let cities = region[0].cities;

            for (i = 0; i < cities.length; i++) {
                let city = cities[i];

                let cityName = city.cityName;
                let cityId = city.id;

                let option = document.createElement("option");
                option.setAttribute("value", cityName);
                option.setAttribute("id", cityId);
                cityList.appendChild(option);
            }

        }

        window.onload = () => {
            getData().then((data) => {
                //fill countries drop down
                populateCountryData(data);

                //await change countries
                countryInput.addEventListener("change", () => {
                    //fill region drop down
                    populateRegionData(data);
                });

                //await change regions
                regionInput.addEventListener("change", () => {
                    //fill city drop down
                    populateCityData(data);
                });

                //await change cities
                cityInput.addEventListener("change", () => {
                    let selectedValue = cityInput.value;
                    let cityId = findOptionId(cityList, selectedValue);
                    cityIdInput.setAttribute("value", cityId);
                });
            }).catch(err => console.error(err));

        }
    </script>
</body>

</html>
