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
    <!--
    <form action="" method="get" id="locationForm">
        <label for="country">Select a Country</label>
        <input list="countryList" name="country" id="country">
        <datalist id="countryList">
           <%/* <c:forEach var="country" items="${countries}">
                <option value="${country.id}">${country.countryName}</option>
            </c:forEach>
            */%>
        </datalist>
    </form>
    -->
    <form action="" method="" id="locationForm">
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

        window.onload = () => {
            //Get country list and populate first option
            getRequestJSON(countryUrl, (response) => {
                let countries = response.Countries;

                for (i = 0; i < countries.length; i++ ) {
                    let country = countries[i];
                    let countryName = country.name;
                    let countryId = country.id;

                    let option = document.createElement("option");
                    option.setAttribute("value", countryName);
                    option.setAttribute("id", countryId);

                    countryList.appendChild(option);
                }
            });

            countryInput.addEventListener("change", () => {
                //console.log(countryInput.value);
                //clear city region options and city options
                let cityOptions = cityList.querySelectorAll("option");
                let regionOptions = regionList.querySelectorAll("option");
                cityOptions.forEach(o => o.remove());
                regionOptions.forEach(o => o.remove());

                //get value + associated id
                let selectedValue = countryInput.value;
                let countryId = findOptionId(countryList, selectedValue);
                console.log(countryId);

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

                //get value + associated id
                let selectedValue = regionInput.value;
                let regionId = findOptionId(regionList, selectedValue);
                console.log(regionId);

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
        }
    </script>
</body>

</html>
