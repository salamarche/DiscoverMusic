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
        let url = "http://localhost:8080/DiscoverMusic_war/services/countries";

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

        window.onload = () => {
            getRequestJSON(url, (response) => {
                let countries = response.Countries;
                let countryList = document.querySelector("#countryList");

                for (i = 0; i < countries.length; i++ ) {
                    let country = countries[i];
                    let countryName = country.name;
                    let countryId = country.id;

                    let option = document.createElement("option");
                    option.setAttribute("id", countryId);
                    option.setAttribute("value", countryName);

                    countryList.appendChild(option);
                }
            });
            let countryInput = document.querySelector("#country");
            countryInput.addEventListener("change", () => {
                //console.log(countryInput.value);

                //TODO: populate region
            })
        }
    </script>
</body>

</html>