let data;
$.ajax({
  url: "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-093?Authorization=CWB-B1446A66-1DE9-4962-8FC7-38B564BA7715&locationId=F-D0047-063&locationName=%E5%8C%97%E6%8A%95%E5%8D%80&elementName=T",
  method: "GET",
  dataType: "json",
  success: function (re) {
    const {body} = document;
    $(document).ready(function() {
      const tr = document.getElementsByClassName("weather-tr-1")[0];
      for (let i = 0; i <14; i+=2) {
        data = re.records.locations[0].location[0].weatherElement[0].time[i].elementValue[0].value;
        let str = "";
        str += `
        <td class="td"> ${data + "°C"} </td>
        `;
        $(tr).append(str);
      }
    });
  }
});

