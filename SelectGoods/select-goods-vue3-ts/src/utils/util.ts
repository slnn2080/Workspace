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
