<script setup lang="ts">
import { reactive } from 'vue'
import { Menu, ArrowDown } from '@element-plus/icons-vue'
import { getAssetsResource } from '@/utils/util.ts'
import Breadcrumb from './Breadcrumb.vue'
import ModulePanel from './ModulePanel.vue'

defineOptions({
  name: 'AppHeader'
})
const lightLogoUrl = getAssetsResource('global-menu-light.png')

const breadcrumbs = reactive<string[]>([
  'homepage',
  'management',
  'list',
  'detail'
])

const top = () => {}
const logout = () => {}
</script>

<template>
  <div class="header__wrapper">
    <div class="header__left">
      <!-- Logo -->
      <div class="header__left__logo">
        <el-button class="header__left__logo__btn">
          <img :src="lightLogoUrl" />
        </el-button>
      </div>
      <!-- Gwes -->
      <div class="header__left__gwes">
        <el-button class="header__left__gwes__btn"> GWES </el-button>
      </div>
      <!-- breadcrumb -->
      <div class="header__left__breadcrumb">
        <Breadcrumb :list="breadcrumbs" fs="18px" />
      </div>
    </div>
    <div class="header__right">
      <!-- error area -->
      <div class="header__right__abnormal">
        <el-dropdown trigger="click" size="large">
          <el-button type="danger" class="header__right__abnormal__btn">
            状態異常発生中
          </el-button>
          <template #dropdown>
            <el-card class="header__right__abnormal__card">
              <div class="content">
                <div class="title">Error Message</div>
                <div class="datetime">2023-10-01 02:20:00</div>
              </div>
            </el-card>
          </template>
        </el-dropdown>
      </div>
      <!-- user info -->
      <div class="header__right__info">
        <span>劉開発テスト（[mm]Optimezer動作確認センター）</span>
      </div>
      <!-- ... area header__abbreviate__menu -->
      <div class="header__right__abbreviate">
        <el-dropdown trigger="click" size="large">
          <el-button type="info" class="header__right__abbreviate__btn">
            <el-icon><Menu /></el-icon>
          </el-button>
          <template #dropdown>
            <div class="header__right__abbreviate__card">
              <ModulePanel />
            </div>
          </template>
        </el-dropdown>
      </div>
      <!-- logout -->
      <div class="header__right__logout">
        <el-dropdown trigger="click" size="large">
          <el-button type="default" class="header__right__logout__btn">
            <el-icon class="el-icon--right">
              <ArrowDown />
            </el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu class="header__right__logout__menu">
              <el-dropdown-item @click="top">トップ</el-dropdown-item>
              <el-dropdown-item @click="logout">ログアウト</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
@mixin resetBtn {
  height: 100%;
  border: none;
}

@mixin elCenter {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.header {
  &__wrapper {
    font-size: 16px;
    height: 100%;

    display: flex;
    justify-content: space-between;
  }

  &__left {
    display: flex;
    &__logo {
      height: 100%;

      &__btn {
        @include resetBtn;
        width: 72px;
        height: 100%;
        padding: 0;
        position: relative;

        img {
          width: 130%;
          height: auto;
          vertical-align: bottom;

          @include elCenter;
        }
      }
    }

    &__gwes {
      padding-right: 30px;
      &__btn {
        @include resetBtn;
        font-size: 1.5em;
      }
    }

    &__breadcrumb {
      display: flex;
      align-items: center;
      padding-right: 30px;
    }
  }
  &__right {
    display: flex;

    &__abnormal {
      display: flex;
      align-items: center;
      padding-right: 30px;

      &__btn {
        height: 40px;
      }

      &__card {
        width: 350px;

        .content {
          & + .content {
            margin-top: 20px;
          }

          .title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 8px;
          }

          .datetime {
            font-size: 14px;
          }
        }
      }
    }

    &__info {
      display: flex;
      align-items: center;
      padding-right: 30px;
    }

    &__abbreviate {
      display: flex;
      align-items: center;
      padding-right: 30px;

      &__btn {
        @include resetBtn;
        font-size: 1.5em;
        height: 40px;
      }

      &__card {
        width: 300px;
      }
    }

    &__logout {
      display: flex;
      align-items: center;
      padding-right: 30px;

      &__btn {
        height: 40px;
        border: none;
      }

      &__menu {
        width: 200px;
      }
    }
  }
}
</style>
