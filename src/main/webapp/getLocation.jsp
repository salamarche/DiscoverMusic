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
        /*TODO: This doesn't have to be this hard! Get one HUGE json
           with all data and use javascript to search the same object for each.
           This should improve performance instead of making so many requests.
        */

        let countryUrl = "http://localhost:8080/DiscoverMusic_war/services/countries";
        let regionUrl = "http://localhost:8080/DiscoverMusic_war/services/regions"
        let cityUrl = "http://localhost:8080/DiscoverMusic_war/services/cities";

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

        window.onload = () => {
            getData().then((data) => {
                //fill countries drop down
                for (i = 0; i < data.length; i++ ) {
                    let country = data[i];
                    let countryName = country.countryName;
                    let countryId = country.id;

                    let option = document.createElement("option");
                    option.setAttribute("value", countryName);
                    option.setAttribute("id", countryId);

                    countryList.appendChild(option);

                }
                //await change countries

                    //fill region drop down

                    //await change regions

                        //fill city drop down

                        //Set hidden inputs
            }).catch(err => console.error(err));


            /*



            console.log(data);

            countryInput.addEventListener("change", () => {
                //console.log(countryInput.value);
                //clear city region options and city options
                let cityOptions = cityList.querySelectorAll("option");
                let regionOptions = regionList.querySelectorAll("option");
                cityOptions.forEach(o => o.remove());
                regionOptions.forEach(o => o.remove());
                cityInput.textContent = "";
                regionInput.textContent = "";
                cityIdInput.setAttribute("value", "");
                regionIdInput.setAttribute("value", "");


                //get value + associated id
                let selectedValue = countryInput.value;
                let countryId = findOptionId(countryList, selectedValue);

                //set hidden input
                countryIdInput.setAttribute("value", countryId);

                //populate region dropdown
                let regionUrlWithValue = regionUrl + "/" + countryId;
                getRequestJSON(regionUrlWithValue, (response) => {
                    let regions = response.Regions;

                    for (i = 0; i < regions.length; i++ ) {
                        let region = regions[i];
                        let regionName = region.name;
                        let regionId = region.id;

                        let option = document.createElement("option");
                        //option.setAttribute("id", countryId);
                        option.setAttribute("value", regionName);
                        option.setAttribute("id", regionId);
                        regionList.appendChild(option);
                    }
                });
            });

            regionInput.addEventListener("change", () => {
                console.log(regionInput.value);
                //clear city options
                let cityOptions = cityList.querySelectorAll("option");
                cityOptions.forEach(o => o.remove());
                cityInput.textContent = "";
                cityIdInput.setAttribute("value", "");

                //get value + associated id
                let selectedValue = regionInput.value;
                let regionId = findOptionId(regionList, selectedValue);
                console.log(regionId);

                //set hidden input
                regionIdInput.setAttribute("value", regionId);


                //populate city dropdown
                let cityUrlWithValue = cityUrl + "/" + regionId;
                getRequestJSON(cityUrlWithValue, (response) => {
                    let cities = response.Cities;

                    for (i = 0; i < cities.length; i++ ) {
                        let city = cities[i];
                        let cityName = city.name;
                        let cityId = city.id;

                        let option = document.createElement("option");

                        option.setAttribute("value", cityName);
                        option.setAttribute("id", cityId);
                        cityList.appendChild(option);
                    }
                });
            });

            cityInput.addEventListener("change", () => {
                //get value + associated id
                let selectedValue = cityInput.value;
                let cityId = findOptionId(cityList, selectedValue);
                console.log(cityId);

                //set hidden input
                cityIdInput.setAttribute("value", cityId);
            });

          */

        }
    </script>
</body>

</html>
