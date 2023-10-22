type getAssetsResourceType = (imgName: string) => string
export const getAssetsResource: getAssetsResourceType = (imgName) => {
  return new URL(`/src/assets/gwes_images/${imgName}`, import.meta.url).href
}

// 封装本地存储 存储数据 和 读取数据 的方法
export const SET_TOKEN = (token: string): void => {
  localStorage.setItem('token', token)
}

export const GET_TOKEN = (): string | null => {
  return localStorage.getItem('token') as string
}

// 判断 当前是哪个时间段
export function getTimeRange(): string {
  const date = new Date()
  const h = date.getHours()

  let range = ''
  if (h < 9) {
    range = '早上'
  } else if (h >= 9 && h <= 14) {
    range = '上午'
  } else if (h > 14 && h <= 18) {
    range = '下午'
  } else {
    range = '晚上'
  }

  return range
}
