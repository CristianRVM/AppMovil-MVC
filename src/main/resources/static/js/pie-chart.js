const EMOJI_TO_NAME = {
  "😄": "Muy feliz",
  "😊": "Feliz",
  "🙂": "Contento",
  "😐": "Neutral",
  "😔": "Desanimado",
  "😢": "Triste",
  "😡": "Enojado",
  "😤": "Frustrado",
  "😴": "Somnoliento",
  "🤒": "Enfermo",
  "😱": "Sorprendido",
  "🥳": "Fiesta"
};


(() => {
  const pieChart = echarts.init(document.getElementById('pie-chart'));

  const option = {
    title: { text: '0%', left: 'center', top: 'center' },
    tooltip: { 
        trigger: 'item', 
            formatter: function (params) {
                const emoji = params.name;
                const emocion = EMOJI_TO_NAME[emoji] || "Desconocido";
                return `${emoji} (${emocion})<br/>Cantidad: ${params.value}<br/>Porcentaje: ${params.percent}%`;
            }},
    series: [{
      type: 'pie',
      radius: ['40%', '70%'],
      label: {
        show: true,
        fontSize: 28, //tamaño del emoji
        formatter: '{b}', // muestra el emoji (name del dato)
      },
      labelLine: {
        length: 10, // largo de la línea
        lineStyle: {
            width: 2         // grosor de la línea
        }
      },
    
      data: []
    }]
  };

  pieChart.setOption(option);

  async function cargarPie(usuarioId, days = 30) {
    const res = await fetch(`/api/estados/resumen?usuarioId=${usuarioId}&days=${days}`);
    if (!res.ok) return console.error('No se pudo obtener resumen');
    const data = await res.json();

    const totalRegs = data.reduce((acc, x) => acc + x.total, 0);
    const seriesData = data.map(x => ({ value: x.total, name: x.emoji }));


    pieChart.setOption({
      title: { text: `${totalRegs} Registros` },
      series: [{ data: seriesData }]
    });
  }

  cargarPie(1, 30);
})();



