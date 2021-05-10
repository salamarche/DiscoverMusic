let countries = [];
let regions = [];
let cities = [];
let fetchedData = [];

const fetchData = async() => {
       
   let response = await fetch("worldcities.json");

   if(response.ok) {
       let json = await response.json();
       assembleData(json);
   } else {
       console.log("an error occured")
   }

    
}


const assembleData = (json) => {
    //console.log(json.length);

    for (i = 0; i < json.length; i++) {
        let region = json[i].admin_name
        let city = json[i].city
        let cityId = json[i].id
        let iso3 = json[i].iso3
        let country = json[i].country
        let lat = json[i].lat
        let lng = json[i].lng

        //console.log(`${region}\n${city}\n${cityId}\n${iso3}\n${country}\n${lat}\n${lng}`)


        if (countries.filter(c => c.iso3 == iso3).length == 0) {
            /*country list does not contain any objects with this country code */
            newCountry = {}
            newCountry.id = countries.length + 1;
            newCountry.name = country;
            newCountry.iso3 = iso3;
            countries.push(newCountry);
        }

        if (regions.filter(r => r.name == region).length == 0) {
            /* region list does not contain any objects with this region name */
            newRegion = {}
            newRegion.id = regions.length + 1;
            newRegion.name = region;
            newRegion.countryId = countries.filter(c => c.iso3 == iso3)[0].id;
            regions.push(newRegion);
           

        }

        newCity = {}
        newCity.id = cityId;
        newCity.name = city;
        newCity.lat = lat;
        newCity.lng = lng;
        newCity.regionId = regions.filter(r => r.name == region)[0].id
        cities.push(newCity);

    } 
    //console.log(countries);
    //console.log(regions);
    //console.log(cities);

    let countryJSON = JSON.stringify(countries)
    let regionJSON = JSON.stringify(regions)
    let cityJSON = JSON.stringify(cities)

    console.log(countryJSON)
    console.log(regionJSON)
    console.log(cityJSON)
    console.log(JSON.stringify(json))




}



window.onload = () => {
    fetchData();
}
