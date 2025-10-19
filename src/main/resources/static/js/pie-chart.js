// Initialize the echarts instance based on the prepared dom
var myChart = echarts.init(document.getElementById('pie-chart'));

// Specify the configuration items and data for the chart
var option = {
    title: {
        text: '75%',
        left: 'center',
        top: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: '{b}: {c} ({d}%)'
    },
    series: [
        {
            type: 'pie',
            data: [
                {value: 90, name: '😔'},
                {value: 270, name: '😊'}
            ],
            color: ['#FFF', '#7ED6A5'],
            radius: ['40%', '70%']
        }
    ]
};

// Display the chart using the configuration items and data just specified.
myChart.setOption(option);

