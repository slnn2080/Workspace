<script setup lang="ts">
import MenuItem from './MenuItem.vue'
// import menuList from '../../../assets/json/menuList'
defineOptions({
  name: 'GwesAppSideBar'
})
/*
function component() {
  return Promise.resolve().then(function () {
    return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index / "./src/blank/index.vue"));
  })
}
*/
const data = [
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_progressSummary',
    treeLevel: 1,
    path: '/gwes/pa/progressSummary',
    name: 'progressSummary',
    title: '進捗サマリ',
    menuKind: '03',
    sortKey: 100,
    hidden: false,
    basePath: 'progressSummary',
    meta: {
      moduleCode: 'pa',
      title: '進捗サマリ',
      screenId: 'progressSummary'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 0
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6MDIrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAyOjI0KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAyOjI0KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjYzMjMxYmI1LWVhNTgt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_summaryDashboard',
        treeLevel: 0,
        path: '/gwes/pa/progressSummary/summaryDashboard',
        name: 'summaryDashboard',
        title: '全体進捗ダッシュボード',
        menuKind: '01',
        sortKey: 101,
        hidden: false,
        basePath: 'progressSummary/summaryDashboard',
        meta: {
          moduleCode: 'pa',
          title: '全体進捗ダッシュボード',
          screenId: 'summaryDashboard'
        },
        parentName: 'progressSummary',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/progressSummaryDashboard/dashboardCenter */ "./src/views/ga/progressSummaryDashboard/dashboardCenter.vue"));\n    });\n  }</pre>',
            _reviveId: 1
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_summaryDashboardByFloor',
        treeLevel: 0,
        path: '/gwes/pa/progressSummary/summaryDashboardByFloor',
        name: 'summaryDashboardByFloor',
        title: 'フロア別進捗ダッシュボード',
        menuKind: '01',
        sortKey: 102,
        hidden: false,
        basePath: 'progressSummary/summaryDashboardByFloor',
        meta: {
          moduleCode: 'pa',
          title: 'フロア別進捗ダッシュボード',
          screenId: 'summaryDashboardByFloor'
        },
        parentName: 'progressSummary',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/progressSummaryDashboard/dashboardCenter */ "./src/views/ga/progressSummaryDashboard/dashboardCenter.vue"));\n    });\n  }</pre>',
            _reviveId: 2
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_progressDetail',
    treeLevel: 1,
    path: '/gwes/pa/progressDetail',
    name: 'progressDetail',
    title: '進捗詳細',
    menuKind: '03',
    sortKey: 200,
    hidden: false,
    basePath: 'progressDetail',
    meta: {
      moduleCode: 'pa',
      title: '進捗詳細',
      screenId: 'progressDetail'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 3
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6MDIrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAyOjI0KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAyOjI0KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjYzMjMxYmI1LWVhNTgt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workPlanListDashboard',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/workPlanListDashboard',
        name: 'workPlanListDashboard',
        title: '計画一覧ダッシュボード',
        menuKind: '01',
        sortKey: 201,
        hidden: false,
        basePath: 'progressDetail/workPlanListDashboard',
        meta: {
          moduleCode: 'pa',
          title: '計画一覧ダッシュボード',
          screenId: 'workPlanListDashboard'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/workPlanListDashboard/index */ "./src/views/ga/workPlanListDashboard/index.vue"));\n    });\n  }</pre>',
            _reviveId: 4
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_summaryDetailsByFloor',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/summaryDetailsByFloor',
        name: 'summaryDetailsByFloor',
        title: '進捗詳細ダッシュボード',
        menuKind: '01',
        sortKey: 202,
        hidden: false,
        basePath: 'progressDetail/summaryDetailsByFloor',
        meta: {
          moduleCode: 'pa',
          title: '進捗詳細ダッシュボード',
          screenId: 'summaryDetailsByFloor'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/summaryDetailsByFloor/index */ "./src/views/ga/summaryDetailsByFloor/index.vue"));\n    });\n  }</pre>',
            _reviveId: 5
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_shipmentCompletedDashboard',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/shipmentCompletedDashboard',
        name: 'shipmentCompletedDashboard',
        title: '便別完了ダッシュボード',
        menuKind: '01',
        sortKey: 203,
        hidden: false,
        basePath: 'progressDetail/shipmentCompletedDashboard',
        meta: {
          moduleCode: 'pa',
          title: '便別完了ダッシュボード',
          screenId: 'shipmentCompletedDashboard'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/shipmentCompletedDashboard */ "./src/views/ga/shipmentCompletedDashboard/index.vue"));\n    });\n  }</pre>',
            _reviveId: 6
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_throughputMonitor',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/throughputMonitor',
        name: 'throughputMonitor',
        title: '作業者別スループットモニタ',
        menuKind: '01',
        sortKey: 204,
        hidden: false,
        basePath: 'progressDetail/throughputMonitor',
        meta: {
          moduleCode: 'pa',
          title: '作業者別スループットモニタ',
          screenId: 'throughputMonitor'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/throughputMonitor/index */ "./src/views/ga/throughputMonitor/index.vue"));\n    });\n  }</pre>',
            _reviveId: 7
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_actualProgressMonitor',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/actualProgressMonitor',
        name: 'actualProgressMonitor',
        title: '進捗モニタ（指示済）',
        menuKind: '01',
        sortKey: 204,
        hidden: false,
        basePath: 'progressDetail/actualProgressMonitor',
        meta: {
          moduleCode: 'pa',
          title: '進捗モニタ（指示済）',
          screenId: 'actualProgressMonitor'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/actualProgressMonitor/index */ "./src/views/ga/actualProgressMonitor/index.vue"));\n    });\n  }</pre>',
            _reviveId: 8
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workerCurrentThroughputMonitor',
        treeLevel: 0,
        path: '/gwes/pa/progressDetail/workerCurrentThroughputMonitor',
        name: 'workerCurrentThroughputMonitor',
        title: '作業者別現在スループットモニタ',
        menuKind: '01',
        sortKey: 205,
        hidden: false,
        basePath: 'progressDetail/workerCurrentThroughputMonitor',
        meta: {
          moduleCode: 'pa',
          title: '作業者別現在スループットモニタ',
          screenId: 'workerCurrentThroughputMonitor'
        },
        parentName: 'progressDetail',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/throughputMonitor/index */ "./src/views/ga/throughputMonitor/index.vue"));\n    });\n  }</pre>',
            _reviveId: 9
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_workPlan',
    treeLevel: 1,
    path: '/gwes/pa/workPlan',
    name: 'workPlan',
    title: '作業計画',
    menuKind: '03',
    sortKey: 300,
    hidden: false,
    basePath: 'workPlan',
    meta: {
      moduleCode: 'pa',
      title: '作業計画',
      screenId: 'workPlan'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 10
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6NDErMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjQzOjIzKzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjQzOjIzKzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjQ5NjU4MDZmLTU5MGItNDUwYS1iOGNmLWY0YWIyMWYwZDA4NiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo0OTY1ODA2Zi01OTBiLTQ1MGEtYjhjZi1mNGFiMjFmMGQwODYiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0OTY1ODA2Zi01OTBiLTQ1MGEtYjhjZi1mNGFiMjFmMGQwODYiPiA8eG1wTU06SGlzdG9yeT4gPHJkZjpTZXE+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJjcmVhdGVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjQ5NjU4MDZmLTU5MGItNDUwYS1iOGNmLWY0YWIyMWYwZDA4NiIgc3RFdnQ6d2hlbj0iMjAyMS0wNy0yMlQxMjozNzo0MSswOTowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIDIxLjIgKE1hY2ludG9zaCkiLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+xl5eAwAAAW1JREFUeJzt3NFtwjAAAFG7YoCO0hFgKjyCM1UzQkZhg/SjUWWFhotiIqf03h8QCD3ZsSOhxnEcg5a9tf4CR2cgYCBgIGAgcCofxBg3f1DO+dflMKW0/UMbnbdc2R1BwEDAQMBAwEAgllfsruu8MQshXK/XnxXQEQQMBAwEDAQMBE58yLr7mr94L7b03pIjCBgIGAgYCBgIrFrF1th7tWp1XkcQMBAwEDAQMBB42iq2JOd83vscJKXUb31vVaDpj/+cPX2ZfaH56y1s3go4xYCBgIFA1TVoutbQ/L7UnKO13VexmhXkCJxiwECgdh/0HkL4mD09pJRuxTHnmnM8Q7ONYviOc7dRDCH0xWM3iq/MQMBAoPYaNIT7jeAwe/x/N4rTatXDMQ9fPzqnGDAQcKMI3CgCpxgwEDAQcKMI3CgCpxgwEDAQMBAwEFi1iq35RfqrcgQBAwEDAQMBA4Hof6B6zBEEDAQMBAwEDAS+ADx/XoSzMLKWAAAAAElFTkSuQmCC',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workPredictionInfoMaster',
        treeLevel: 0,
        path: '/gwes/pa/workPlan/workPredictionInfoMaster',
        name: 'workPredictionInfoMaster',
        title: '作業予測情報メンテナンス',
        menuKind: '01',
        sortKey: 301,
        hidden: false,
        basePath: 'workPlan/workPredictionInfoMaster',
        meta: {
          moduleCode: 'pa',
          title: '作業予測情報メンテナンス',
          screenId: 'workPredictionInfoMaster'
        },
        parentName: 'workPlan',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/dyas_work_prediction_info_master/index */ "./src/views/ga/dyas_work_prediction_info_master/index.vue"));\n    });\n  }</pre>',
            _reviveId: 11
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_personnelSimulation',
        treeLevel: 0,
        path: '/gwes/pa/workPlan/personnelSimulation',
        name: 'personnelSimulation',
        title: '要員シミュレーション',
        menuKind: '01',
        sortKey: 302,
        hidden: false,
        basePath: 'workPlan/personnelSimulation',
        meta: {
          moduleCode: 'pa',
          title: '要員シミュレーション',
          screenId: 'personnelSimulation'
        },
        parentName: 'workPlan',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/personnelSimulation/index */ "./src/views/ga/personnelSimulation/index.vue"));\n    });\n  }</pre>',
            _reviveId: 12
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_workForecast',
    treeLevel: 1,
    path: '/gwes/pa/workForecast',
    name: 'workForecast',
    title: '作業予測',
    menuKind: '03',
    sortKey: 400,
    hidden: false,
    basePath: 'workForecast',
    meta: {
      moduleCode: 'pa',
      title: '作業予測',
      screenId: 'workForecast'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 13
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6MjIrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOmExMTllNTc3LWVjZWIt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_forecastModelCreate',
        treeLevel: 0,
        path: '/gwes/pa/workForecast/forecastModelCreate',
        name: 'forecastModelCreate',
        title: '作業量予測モデル作成',
        menuKind: '01',
        sortKey: 401,
        hidden: false,
        basePath: 'workForecast/forecastModelCreate',
        meta: {
          moduleCode: 'pa',
          title: '作業量予測モデル作成',
          screenId: 'forecastModelCreate'
        },
        parentName: 'workForecast',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/fcstModelCreate/index */ "./src/views/ga/fcstModelCreate/index.vue"));\n    });\n  }</pre>',
            _reviveId: 14
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_forecastMaster',
        treeLevel: 0,
        path: '/gwes/pa/workForecast/forecastMaster',
        name: 'forecastMaster',
        title: '作業量予測マスタメンテナンス',
        menuKind: '01',
        sortKey: 402,
        hidden: false,
        basePath: 'workForecast/forecastMaster',
        meta: {
          moduleCode: 'pa',
          title: '作業量予測マスタメンテナンス',
          screenId: 'forecastMaster'
        },
        parentName: 'workForecast',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/fcstMaster/index */ "./src/views/ga/fcstMaster/index.vue"));\n    });\n  }</pre>',
            _reviveId: 15
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_forecastResultConfirmation',
        treeLevel: 0,
        path: '/gwes/pa/workForecast/forecastResultConfirmation',
        name: 'forecastResultConfirmation',
        title: '作業量予測計算処理結果確認',
        menuKind: '01',
        sortKey: 403,
        hidden: false,
        basePath: 'workForecast/forecastResultConfirmation',
        meta: {
          moduleCode: 'pa',
          title: '作業量予測計算処理結果確認',
          screenId: 'forecastResultConfirmation'
        },
        parentName: 'workForecast',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/fcstResultConfirmation/index */ "./src/views/ga/fcstResultConfirmation/index.vue"));\n    });\n  }</pre>',
            _reviveId: 16
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_forecastResultInquiry',
        treeLevel: 0,
        path: '/gwes/pa/workForecast/forecastResultInquiry',
        name: 'forecastResultInquiry',
        title: '作業量予測実績照会',
        menuKind: '01',
        sortKey: 404,
        hidden: false,
        basePath: 'workForecast/forecastResultInquiry',
        meta: {
          moduleCode: 'pa',
          title: '作業量予測実績照会',
          screenId: 'forecastResultInquiry'
        },
        parentName: 'workForecast',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/fcstResultInquiry/index */ "./src/views/ga/fcstResultInquiry/index.vue"));\n    });\n  }</pre>',
            _reviveId: 17
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_kpiMaster',
    treeLevel: 1,
    path: '/gwes/pa/kpiMaster',
    name: 'kpiMaster',
    title: '目標KPIメンテナンス',
    menuKind: '03',
    sortKey: 500,
    hidden: false,
    basePath: 'kpiMaster',
    meta: {
      moduleCode: 'pa',
      title: '目標KPIメンテナンス',
      screenId: 'kpiMaster'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 18
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6MjIrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOmExMTllNTc3LWVjZWIt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workerManagementMaster',
        treeLevel: 0,
        path: '/gwes/pa/kpiMaster/workerManagementMaster',
        name: 'workerManagementMaster',
        title: '場所別作業別管理マスタメンテナンス',
        menuKind: '01',
        sortKey: 501,
        hidden: false,
        basePath: 'kpiMaster/workerManagementMaster',
        meta: {
          moduleCode: 'pa',
          title: '場所別作業別管理マスタメンテナンス',
          screenId: 'workerManagementMaster'
        },
        parentName: 'kpiMaster',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/place_work_management_master/index */ "./src/views/ga/place_work_management_master/index.vue"));\n    });\n  }</pre>',
            _reviveId: 19
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workerTargetMaster',
        treeLevel: 0,
        path: '/gwes/pa/kpiMaster/workerTargetMaster',
        name: 'workerTargetMaster',
        title: '作業者別目標マスタメンテナンス',
        menuKind: '01',
        sortKey: 502,
        hidden: false,
        basePath: 'kpiMaster/workerTargetMaster',
        meta: {
          moduleCode: 'pa',
          title: '作業者別目標マスタメンテナンス',
          screenId: 'workerTargetMaster'
        },
        parentName: 'kpiMaster',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/worker_target_master/index */ "./src/views/ga/worker_target_master/index.vue"));\n    });\n  }</pre>',
            _reviveId: 20
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workPlanlineMaintain',
        treeLevel: 0,
        path: '/gwes/pa/kpiMaster/workPlanlineMaintain',
        name: 'workPlanlineMaintain',
        title: '作業計画線メンテナンス',
        menuKind: '01',
        sortKey: 503,
        hidden: false,
        basePath: 'kpiMaster/workPlanlineMaintain',
        meta: {
          moduleCode: 'pa',
          title: '作業計画線メンテナンス',
          screenId: 'workPlanlineMaintain'
        },
        parentName: 'kpiMaster',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/workPlanlineMaintain/index */ "./src/views/ga/workPlanlineMaintain/index.vue"));\n    });\n  }</pre>',
            _reviveId: 21
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_master',
    treeLevel: 1,
    path: '/gwes/pa/master',
    name: 'master',
    title: 'マスタメンテナンス',
    menuKind: '03',
    sortKey: 600,
    hidden: false,
    basePath: 'master',
    meta: {
      moduleCode: 'pa',
      title: 'マスタメンテナンス',
      screenId: 'master'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 22
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6Mzc6MjIrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAzOjI3KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOmExMTllNTc3LWVjZWIt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_workerMaster',
        treeLevel: 0,
        path: '/gwes/pa/master/workerMaster',
        name: 'workerMaster',
        title: '作業者マスタメンテナンス',
        menuKind: '01',
        sortKey: 601,
        hidden: false,
        basePath: 'master/workerMaster',
        meta: {
          moduleCode: 'pa',
          title: '作業者マスタメンテナンス',
          screenId: 'workerMaster'
        },
        parentName: 'master',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/worker_master/index */ "./src/views/ga/worker_master/index.vue"));\n    });\n  }</pre>',
            _reviveId: 23
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_calendarMaster',
        treeLevel: 0,
        path: '/gwes/pa/master/calendarMaster',
        name: 'calendarMaster',
        title: 'カレンダマスタメンテナンス',
        menuKind: '01',
        sortKey: 602,
        hidden: false,
        basePath: 'master/calendarMaster',
        meta: {
          moduleCode: 'pa',
          title: 'カレンダマスタメンテナンス',
          screenId: 'calendarMaster'
        },
        parentName: 'master',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/calendar_master/index */ "./src/views/ga/calendar_master/index.vue"));\n    });\n  }</pre>',
            _reviveId: 24
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_calendarEventMaster',
        treeLevel: 0,
        path: '/gwes/pa/master/calendarEventMaster',
        name: 'calendarEventMaster',
        title: 'イベントマスタメンテナンス',
        menuKind: '01',
        sortKey: 603,
        hidden: false,
        basePath: 'master/calendarEventMaster',
        meta: {
          moduleCode: 'pa',
          title: 'イベントマスタメンテナンス',
          screenId: 'calendarEventMaster'
        },
        parentName: 'master',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/calendarEventMaster/index */ "./src/views/ga/calendarEventMaster/index.vue"));\n    });\n  }</pre>',
            _reviveId: 25
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_displayOrderMaster',
        treeLevel: 0,
        path: '/gwes/pa/master/displayOrderMaster',
        name: 'displayOrderMaster',
        title: '表示順マスタメンテナンス',
        menuKind: '01',
        sortKey: 604,
        hidden: false,
        basePath: 'master/displayOrderMaster',
        meta: {
          moduleCode: 'pa',
          title: '表示順マスタメンテナンス',
          screenId: 'displayOrderMaster'
        },
        parentName: 'master',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/displayOrderMaster/index */ "./src/views/ga/displayOrderMaster/index.vue"));\n    });\n  }</pre>',
            _reviveId: 26
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_autoPlayMaster',
        treeLevel: 0,
        path: '/gwes/pa/master/autoPlayMaster',
        name: 'autoPlayMaster',
        title: 'オートプレイマスタメンテナンス',
        menuKind: '01',
        sortKey: 605,
        hidden: false,
        basePath: 'master/autoPlayMaster',
        meta: {
          moduleCode: 'pa',
          title: 'オートプレイマスタメンテナンス',
          screenId: 'autoPlayMaster'
        },
        parentName: 'master',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/autoPlayMaster/index */ "./src/views/ga/autoPlayMaster/index.vue"));\n    });\n  }</pre>',
            _reviveId: 27
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_autoPlayes',
    treeLevel: 1,
    path: '/gwes/pa/autoPlayes',
    name: 'autoPlayes',
    title: 'オートプレイ',
    menuKind: '03',
    sortKey: 700,
    hidden: false,
    basePath: 'autoPlayes',
    meta: {
      moduleCode: 'pa',
      title: 'オートプレイ',
      screenId: 'autoPlayes'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 28
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6MzU6MzkrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAwOjUyKzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAwOjUyKzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjJhZTE4MzI2LWQzYWQt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_autoPlay',
        treeLevel: 0,
        path: '/gwes/pa/autoPlayes/autoPlay',
        name: 'autoPlay',
        title: 'オートプレイ',
        menuKind: '01',
        sortKey: 701,
        hidden: false,
        basePath: 'autoPlayes/autoPlay',
        meta: {
          moduleCode: 'pa',
          title: 'オートプレイ',
          screenId: 'autoPlay'
        },
        parentName: 'autoPlayes',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/ga/autoPlay/index */ "./src/views/ga/autoPlay/index.vue"));\n    });\n  }</pre>',
            _reviveId: 29
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  },
  {
    moduleCode: 'pa',
    language: 'ja',
    key: 'pa_1_settings',
    treeLevel: 1,
    path: '/gwes/pa/settings',
    name: 'settings',
    title: '管理',
    menuKind: '03',
    sortKey: 800,
    hidden: false,
    basePath: 'settings',
    meta: {
      moduleCode: 'pa',
      title: '管理',
      screenId: 'settings'
    },
    parentName: null,
    component: {
      _custom: {
        type: 'function',
        display: '<span style="opacity:.5;">function</span> component()',
        tooltip:
          '<pre>function component() {\n        return Promise.resolve().then(function () {\n          return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/blank/index */ "./src/blank/index.vue"));\n        });\n      }</pre>',
        _reviveId: 30
      }
    },
    icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAFGmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNi4wLWMwMDIgNzkuMTY0NDYwLCAyMDIwLzA1LzEyLTE2OjA0OjE3ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RFdnQ9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZUV2ZW50IyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgMjEuMiAoTWFjaW50b3NoKSIgeG1wOkNyZWF0ZURhdGU9IjIwMjEtMDctMjJUMTI6MzY6MzgrMDk6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDIxLTA3LTIyVDEzOjAxOjM1KzA5OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDIxLTA3LTIyVDEzOjAxOjM1KzA5OjAwIiBkYzpmb3JtYXQ9ImltYWdlL3BuZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgcGhvdG9zaG9wOklDQ1Byb2ZpbGU9InNSR0IgSUVDNjE5NjYtMi4xIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjEyMDM4YjdmLTUyMzEt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',
    hasSubMenu: true,
    isExternal: false,
    children: [
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_switchCenter',
        treeLevel: 0,
        path: '/gwes/pa/settings/switchCenter',
        name: 'switchCenter',
        title: 'センター切替',
        menuKind: '01',
        sortKey: 801,
        hidden: false,
        basePath: 'settings/switchCenter',
        meta: {
          moduleCode: 'pa',
          title: 'センター切替',
          screenId: 'switchCenter'
        },
        parentName: 'settings',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/masterUser/switchCenter/index */ "./src/views/masterUser/switchCenter/index.vue"));\n    });\n  }</pre>',
            _reviveId: 31
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_setting',
        treeLevel: 0,
        path: '/gwes/pa/settings/setting',
        name: 'setting',
        title: '表示設定',
        menuKind: '01',
        sortKey: 802,
        hidden: false,
        basePath: 'settings/setting',
        meta: {
          moduleCode: 'pa',
          title: '表示設定',
          screenId: 'setting'
        },
        parentName: 'settings',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/setting/index */ "./src/views/setting/index.vue"));\n    });\n  }</pre>',
            _reviveId: 32
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      },
      {
        moduleCode: 'pa',
        language: 'ja',
        key: 'pa_0_generalSearch',
        treeLevel: 0,
        path: '/gwes/pa/settings/generalSearch',
        name: 'generalSearch',
        title: '汎用検索',
        menuKind: '01',
        sortKey: 803,
        hidden: false,
        basePath: 'settings/generalSearch',
        meta: {
          moduleCode: 'pa',
          title: '汎用検索',
          screenId: 'generalSearch'
        },
        parentName: 'settings',
        component: {
          _custom: {
            type: 'function',
            display: '<span style="opacity:.5;">function</span> component()',
            tooltip:
              '<pre>function component() {\n    return Promise.resolve().then(function () {\n      return (0, _interopRequireWildcard2["default"])(__webpack_require__(/*! @/views/generalSearch/index */ "./src/views/generalSearch/index.vue"));\n    });\n  }</pre>',
            _reviveId: 33
          }
        },
        icon: null,
        hasSubMenu: false,
        isExternal: false,
        children: []
      }
    ]
  }
]
</script>

<template>
  <div class="side-bar__wrapper">
    <el-scrollbar>
      <el-menu default-active="2" class="side-bar__menu">
        <MenuItem :list="data" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style scoped lang="scss">
.side-bar {
  &__menu {
    border-right: none;
  }
}
</style>
