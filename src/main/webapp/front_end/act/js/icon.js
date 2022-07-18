let icondata;
let str = "";
$.ajax({
  url: "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-093?Authorization=CWB-B1446A66-1DE9-4962-8FC7-38B564BA7715&format=JSON&locationId=F-D0047-063&locationName=%E5%8C%97%E6%8A%95%E5%8D%80&elementName=Wx",
  method: "GET",
  dataType: "json",
  success: function (re) {
    const {body} = document;
    $(document).ready(function() {
      const icon = document.getElementsByClassName("icon")[0];
      for (let i = 1; i < 14; i=i+2) {
        icondata = re.records.locations[0].location[0].weatherElement[0].time[i].elementValue[1].value;
        if( icondata == 1 || icondata == 2 || icondata == 3) {
        str += `
        <img class="icon-img" src="/CGA102G2/images/sun.png">  
        `;
      }
      else if(icondata == 4 || icondata == 5 || icondata == 6 || icondata == 7){
        str += `
        <img class="icon-img" src="/CGA102G2/images/cloud.png">  
        `;
      }else{
        str += `
        <img class="icon-img" src="/CGA102G2/images/rain.png">  
        `;
      }
      }
      $(icon).append(str);
    });
  }
});