$(document).ready(function() {
  const tr = document.getElementsByClassName("weather-tr")[0];
  let str = "";
  let date = new Date();
  let day = date.getDay();
  const myday = ["今天", "週一", "週二", "週三", "週四", "週五", "週六"];
  const myday1 = ["今天", "週二", "週三", "週四", "週五", "週六", "週日"];
  const myday2 = ["今天", "週三", "週四", "週五", "週六", "週日", "週一"];
  const myday3 = ["今天", "週四", "週五", "週六", "週日", "週一", "週二"];
  const myday4 = ["今天", "週五", "週六", "週日", "週一", "週二", "週三"];
  const myday5 = ["今天", "週六", "週日", "週一", "週二", "週三", "週四"];
  const myday6 = ["今天", "週日", "週一", "週二", "週三", "週四", "週五"];
  for (let i = 0; i < myday.length; i++) {
    switch (day) {
      case 0:
        str += `
        <th class="th"> ${myday[i]} </th>
        `;
        break;
      case 1:
        str += `
        <th class="th"> ${myday1[i]} </th>
        `;
        break;
      case 2:
        str += `
        <th class="th"> ${myday2[i]} </th>
        `;
        break;
      case 3:
        str += `
        <th class="th"> ${myday3[i]} </th>
        `;
        break;
      case 4:
        str += `
        <th class="th"> ${myday4[i]} </th>
        `;
        break;
      case 5:
        str += `
        <th class="th"> ${myday5[i]} </th>
        `;
        break;
      case 6:
        str += `
        <th class="th"> ${myday6[i]} </th>
        `;
        break;
    }
  }
$(tr).append(str);
});
