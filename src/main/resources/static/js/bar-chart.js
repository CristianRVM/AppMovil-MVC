// Initialize the echarts instance based on the prepared dom
var myChart = echarts.init(document.getElementById('bar-chart'));

// Specify the configuration items and data for the chart
var option = {
    title: {
        text: 'Estado de animo'
    },
    tooltip: {},
    xAxis: {
        data: ['Enero', 'Feb', 'Marzo', 'Abril', 'Junio', 'Julio'],

    },
    yAxis: {},
    series: [
        {
            name: 'sales',
            type: 'bar',
            data: [5, 20, 26, 10, 10, 20],
            color: ['#4C9F38']
        }
    ]
};

// Display the chart using the configuration items and data just specified.
myChart.setOption(option);
