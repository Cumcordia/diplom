<template>
  <div class="content">
    <div class="banner-new-items">
      <div class="banner-title">
        <router-link
          :to="{ path: '/general', query: { gender: 'all', category: null, collection: 'new' } }"
          class="category-link"
          @click="handleMenuItemClick"
        >
          <h2>НОВИНКИ</h2>
          <h4>Посмотреть все</h4>
        </router-link>
      </div>
      <div class="banner-img">
        <img src="@/assets/img/banner.jpg" alt="" />
      </div>
    </div>
    <div class="categories">
      <div class="category-title">
        <h2>ТАКЖЕ ПОСМОТРИТЕ</h2>
      </div>
      <div class="categories">
        <ul class="category-items">
          <li>
            <router-link
              :to="{ path: '/general', query: { gender: 'all', category: 'coat' } }"
              class="category-link"
              @click="handleMenuItemClick"
            >
              <img src="@/assets/img/category-item1.png" alt="" />
              <h3>Пальто</h3>
            </router-link>
          </li>
          <li>
            <router-link
              :to="{ path: '/general', query: { gender: 'all', category: 'shoes' } }"
              class="category-link"
              @click="handleMenuItemClick"
            >
              <img src="@/assets/img/category-item2.png" alt="" />
              <h3>Обувь</h3>
            </router-link>
          </li>
          <li>
            <router-link
              :to="{ path: '/general', query: { gender: 'all', category: 'tshirts' } }"
              class="category-link"
              @click="handleMenuItemClick"
            >
              <img src="@/assets/img/category-item3.png" alt="" />
              <h3>Футболки</h3>
            </router-link>
          </li>
          <li>
            <router-link
              :to="{ path: '/general', query: { gender: 'all', category: 'accessories' } }"
              class="category-link"
              @click="handleMenuItemClick"
            >
              <img src="@/assets/img/category-item4.png" alt="" />
              <h3>Аксессуары</h3>
            </router-link>
          </li>
          <li>
            <router-link
              :to="{ path: '/general', query: { gender: 'all', category: 'jeans' } }"
              class="category-link"
              @click="handleMenuItemClick"
            >
              <img src="@/assets/img/category-item5.png" alt="" />
              <h3>Джинсы</h3>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
    <div class="collections">
      <div class="collections-items collection-1">
        <div class="collections-title">
          <router-link
            :to="{ path: '/general', query: { gender: 'all', category: null, collection: 'stwd' } }"
            class="category-link"
            @click="handleMenuItemClick"
          >
            <h2>STWD</h2>
            <br />
            <p>Посмотреть все</p>
          </router-link>
        </div>
      </div>
      <div class="collections-items collection-2">
        <div class="collections-title">
          <router-link
            :to="{
              path: '/general',
              query: { gender: 'all', category: null, collection: 'pacific' },
            }"
            class="category-link"
            @click="handleMenuItemClick"
          >
            <h2>Pacific Republic</h2>
            <p>Посмотреть все</p>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.banner-new-items {
  position: relative;
  text-align: center;
}

.banner-title {
  position: absolute;
  bottom: 250px; /* Отступ снизу */
  left: 50%;
  transform: translateX(-50%);
  color: white;
  font-size: 2rem;
  font-weight: bold;
  padding: 10px 20px;
  border-radius: 5px;
  text-align: center;
}

.banner-title h4 {
  font-size: 1rem;
  margin-top: 5px;
  text-decoration: underline;
  cursor: pointer;
}

.banner-img img {
  max-width: 100%; /* Чтобы изображение не выходило за границы контейнера */
  height: auto; /* Автоматическая высота в зависимости от пропорций */
  display: block;
  object-fit: cover; /* Обрезка изображения без искажения */
}

.category-title {
  font-family: 'Oswald', sans-serif;
  text-align: center;
}

.category-items {
  display: flex;
  gap: 15px;
  list-style: none;
  padding: 0;
  justify-content: center;
}

.category-items li {
  flex: 1;
  max-width: 300px;
}

.category-items li h3 {
  text-align: center;
  font-family: 'Tinos', serif;
}

.category-link {
  text-decoration: none;
  color: inherit;
}

.category-link:hover,
.category-link:focus {
  text-decoration: none; /* на хувере тоже без подчёркивания */
}

.category-items img {
  width: 100%;
  height: auto;
  display: block;
  border-radius: 10px;
}

.collections {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.collections-items {
  position: relative;
  text-align: center;
  width: 100%;
  height: 90vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

/* Добавлен параллакс */
.collections-items::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  z-index: -1;
}

/* Фоновые изображения */
.collection-1::before {
  background-image: url('@/assets/img/banner1.jpg');
}

.collection-2::before {
  background-image: url('@/assets/img/banner2.jpg');
}

.collections-title {
  color: white;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.collections-title h2 {
  font-family: 'Oswald', sans-serif;
  font-size: 45px;
  margin: 0;
  cursor: pointer;
  color: #333333;
}

.collections-title p {
  font-family: 'Oswald', sans-serif;
  font-size: 20px;
  cursor: pointer;
  color: black;
}
</style>
