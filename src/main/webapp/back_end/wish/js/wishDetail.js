const vote = document.querySelector('#vote');
const message = document.querySelector('#message');


// 投票結果
const voteResult = {
    '第一項': 20,
    '第二項': 10,
    '第三項': 0,
    '第四項': 3,
    '第五項': 16
}
let sumVote = 0;
for (let pos in voteResult)
    sumVote += voteResult[pos];

let str1 = '';
for (let pos in voteResult) {
    str1 += `<div>
    <h3>選項: ${pos}</h3>
        <div class="color" style="height: 20px; width: ${voteResult[pos] * 10}px; background-color: rgb(160, 188, 194);">
        <h5>票數: ${voteResult[pos]}</h5>
        </div>
    </div>`;
    // str += pos + ': ' + voteResult[pos] + '<br>';
}
vote.innerHTML = str1;

// 留言

const voteMessage = {
    1: '希望還有機會!!',
    2: '加油加油',
    3: 'KKKKKKKKKKKKKKKKK',
    4: '第一',
    5: '什麼時候結束'
}

let str2 = '<table><tr><th id="memNo">會員編號</th><th id="memMsg">訊息</th>';


for (let mem in voteMessage) {
    str2 += `<tr>
    <td>${mem}</td>
    <td>${voteMessage[mem]}</td>
    </tr>`;
    // str += pos + ': ' + voteResult[pos] + '<br>';
}
str2 += '</tr></table>';
message.innerHTML = str2;