export default {
  textStyle: {
    fontFamily:
      '"游ゴシック体", "Yu Gothic", YuGothic, "Hiragino Kaku Gothic ProN", "Hiragino Sans", sans-serif',
    fontSize: 12,
    fontStyle: 'normal',
    fontWeight: 'normal',
    color: '#000'
  },
  animation: 'auto',
  animationDuration: 1000,
  animationDurationUpdate: 300,
  animationEasing: 'exponentialOut',
  animationEasingUpdate: 'cubicOut',
  animationThreshold: 2000,
  progressiveThreshold: 3000,
  progressive: 400,
  hoverLayerThreshold: 3000,
  useUTC: false,
  color: [
    '#0099ff',
    '#00cc99',
    '#6600ff',
    '#a0a7e6',
    '#c4ebad',
    '#96dee8',
    '#73a373',
    '#73b9bc',
    '#7289ab'
  ],
  backgroundColor: '#ffffff',
  graph: {
    color: [
      '#0099ff',
      '#00cc99',
      '#6600ff',
      '#a0a7e6',
      '#c4ebad',
      '#96dee8',
      '#73a373',
      '#73b9bc',
      '#7289ab'
    ]
  },
  tooltip: [
    {
      trigger: 'axis',
      axisPointer: {
        lineStyle: {
          color: '#000'
        },
        crossStyle: {
          color: '#000',
          width: 1,
          type: 'dashed',
          textStyle: {}
        },
        label: {
          color: '#000'
        },
        type: 'line',
        axis: 'auto',
        animation: 'auto',
        animationDurationUpdate: 200,
        animationEasingUpdate: 'exponentialOut'
      },
      zlevel: 0,
      z: 60,
      show: true,
      showContent: true,
      triggerOn: 'mousemove|click',
      alwaysShowContent: false,
      displayMode: 'single',
      renderMode: 'auto',
      confine: false,
      showDelay: 0,
      hideDelay: 100,
      transitionDuration: 0.4,
      enterable: false,
      backgroundColor: 'rgba(50,50,50,0.7)',
      borderColor: '#333',
      borderRadius: 4,
      borderWidth: 0,
      padding: 5,
      extraCssText: '',
      textStyle: {
        color: '#fff',
        fontSize: 14
      }
    }
  ],
  series: [
    {
      type: 'bar',
      name: 'showMainBar',
      xAxisIndex: 0,
      label: {
        show: true,
        position: 'right',
        fontSize: 15
      },
      encode: {
        x: 'thoughputActual',
        y: 'workerLabel'
      },
      markPoint: {
        id: 'ID003',
        data: [
          {
            xAxis: 50,
            yAxis: 10
          }
        ],
        symbolSize: 0.1,
        label: {
          show: true,
          formatter: '全体目標スループット'
        }
      }
    }
  ],
  dataset: [
    {
      id: 'ID002',
      source: [
        [
          'dataId',
          'workerLabel',
          'thoughputActual',
          'thoughputTarget',
          'dataType'
        ],
        ['1', '1', '14782.61', '', '1'],
        ['worker@002', '作業者002', '2235.51', '', '1'],
        ['A00003', '作業者A3', '1460.67', '', '1'],
        ['A00005', '作業者A5', '828.73', '', '1'],
        ['11', '11', '819.17', '', '1'],
        ['A00004', 'A00004', '742.86', '', '1'],
        ['worker@003', '作業者003', '652.39', '', '1'],
        ['00001', '00001', '466.67', '', '1'],
        ['00002', '00002', '389.12', '', '1'],
        ['worker@005', '作業者005', '385.37', '', '1'],
        ['', '', '100.00', '', '0']
      ]
    }
  ],
  yAxis: [
    {
      id: 'ID001',
      type: 'category',
      inverse: true,
      axisLine: {
        lineStyle: {
          color: '#000',
          width: 1,
          type: 'solid'
        },
        show: true,
        onZero: true,
        onZeroAxisIndex: null,
        symbol: ['none', 'none'],
        symbolSize: [10, 15]
      },
      axisTick: {
        lineStyle: {
          color: '#000',
          width: 1
        },
        alignWithLabel: false,
        interval: 'auto',
        show: true,
        inside: false,
        length: 5
      },
      axisLabel: {
        textStyle: {
          color: '#000'
        },
        color: '#000',
        interval: 'auto',
        show: true,
        inside: false,
        rotate: 0,
        showMinLabel: null,
        showMaxLabel: null,
        margin: 8,
        fontSize: 12
      },
      splitLine: {
        show: false,
        lineStyle: {
          type: 'dashed',
          color: '#aaa',
          width: 1
        }
      },
      splitArea: {
        areaStyle: {
          color: '#000'
        },
        show: false
      },
      boundaryGap: true,
      deduplication: null,
      show: true,
      zlevel: 0,
      z: 0,
      name: '',
      nameLocation: 'end',
      nameRotate: null,
      nameTruncate: {
        maxWidth: null,
        ellipsis: '...',
        placeholder: '.'
      },
      nameTextStyle: {},
      nameGap: 15,
      silent: false,
      triggerEvent: false,
      tooltip: {
        show: false
      },
      axisPointer: {
        status: 'hide',
        value: 10
      },
      offset: 0,
      rangeEnd: null,
      rangeStart: null,
      max: null
    }
  ],
  xAxis: [
    {
      axisLine: {
        lineStyle: {
          color: '#000',
          width: 1,
          type: 'solid'
        },
        show: true,
        onZero: true,
        onZeroAxisIndex: null,
        symbol: ['none', 'none'],
        symbolSize: [10, 15]
      },
      axisTick: {
        lineStyle: {
          color: '#000',
          width: 1
        },
        show: true,
        inside: false,
        length: 5
      },
      axisLabel: {
        textStyle: {
          color: '#000'
        },
        color: '#000',
        show: true,
        inside: false,
        rotate: 0,
        showMinLabel: null,
        showMaxLabel: null,
        margin: 8,
        fontSize: 12
      },
      splitLine: {
        lineStyle: {
          type: 'dashed',
          color: '#aaa',
          width: 1
        },
        show: true
      },
      splitArea: {
        areaStyle: {
          color: '#000'
        },
        show: false
      },
      boundaryGap: [0, 0],
      splitNumber: 5,
      show: true,
      zlevel: 0,
      z: 0,
      inverse: false,
      name: '',
      nameLocation: 'end',
      nameRotate: null,
      nameTruncate: {
        maxWidth: null,
        ellipsis: '...',
        placeholder: '.'
      },
      nameTextStyle: {},
      nameGap: 15,
      silent: false,
      triggerEvent: false,
      tooltip: {
        show: false
      },
      axisPointer: {},
      offset: 0,
      type: 'value',
      rangeEnd: null,
      rangeStart: null
    }
  ],
  grid: [
    {
      show: false,
      zlevel: 0,
      z: 0,
      left: '10%',
      top: 60,
      right: '10%',
      bottom: 60,
      containLabel: false,
      backgroundColor: 'rgba(0,0,0,0)',
      borderWidth: 1,
      borderColor: '#ccc'
    }
  ]
}
