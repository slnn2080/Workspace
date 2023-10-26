// 这里是基于 vueuse 框架中的 useFetch api 封装的请求
import { createFetch, CreateFetchOptions, UseFetchOptions } from '@vueuse/core'
// import { objectToSearch } from '@/utils';
// import type { IFeatchParams } from './types';

class Fetch {
  // Fetch.instance
  instance

  constructor(params: CreateFetchOptions) {
    const { baseUrl, options } = params
    this.instance = createFetch({
      baseUrl,
      options
    })
  }
}

export default Fetch
