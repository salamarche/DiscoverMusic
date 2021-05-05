<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!--<form action="discoverAction" method="post" id="locationForm">-->
        <div class="form-group">
            <label for="country">Select a Country</label>
            <input class="form-control" list="countryList" name="country" id="country" required>
            <datalist id="countryList">
            </datalist>
        </div>

        <div class="form-group">
            <label for="region">Select a State/ Providence/ Region</label>
            <input class="form-control" list="regionList" name="region" id="region" required>
            <datalist id="regionList">
            </datalist>

        </div>
        <div class="form-group">
            <label for="city">Select a City</label>
            <input class="form-control" list="cityList" name="city" id="city" required>
            <datalist id="cityList">
            </datalist>
        </div>

        <input class="hidden" id="selectedCountryId" name="selectedCountryId" value="">
        <input class="hidden" id="selectedRegionId" name="selectedRegionId" value="">
        <input class="hidden" id="selectedCityId" name="selectedCityId" value="">

        <!--<input class="btn btn-primary mb-2" type="submit" value="enter location">-->
    <!--</form>-->

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


        const readFromFile = (callBack) => {
            fetch("data/countries.json")
                .then(response => response.json())
                .then(data => callBack(data));
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
                readFromFile((response) => {
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
