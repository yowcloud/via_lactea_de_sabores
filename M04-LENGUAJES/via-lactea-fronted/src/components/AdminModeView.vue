<template>
    <div>
    <header>
      <nav class="navbar navbar-expand-sm navbar-dark fixed-top bg-primary container-fluid p-2 " >
        <div>
          <a class="navbar-brand" href="#"><img width="50px" height="50px" src="../assets/img/logo_via_lactea.png" alt="logo"></a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link to="/home" class="nav-link">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/menu" class="nav-link">Menu</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/sala" class="nav-link">Sala</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/reserva" class="nav-link">Reserva</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/about" class="nav-link">About</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/admin" class="nav-link">AdminMode</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/mongo" class="nav-link">Mongo</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/perfil" class="nav-link">Perfil</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/" class="nav-link">Logout</router-link>
            </li>
          </ul>
        </div>
      </nav>
    </header>

    <div class="main-content bg-dark">
      <h2 class="text-white">Home</h2>
      <p class="text-white">Bienvenido a la página de inicio. ¡Has iniciado sesión correctamente!</p>

      <div class="home-content">
        <div class="section">
          <h3>Sobre Nosotros</h3>
          <p>En nuestro restaurante, ofrecemos una experiencia culinaria única con ingredientes frescos y de alta calidad.</p>
        </div>
        <div class="section">
          <h3>Menú Destacado</h3>
          <p>Descubre nuestros platos más populares y las especialidades del chef.</p>
            <ul class="list-group">
              <li class="list-group-item">Agujero Negro</li>
              <li class="list-group-item">Supernova</li>
              <li class="list-group-item">Galaxia Espiral</li>
            </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // No necesitas lógica específica en este componente
};
</script>


<style scoped>
@import "../assets/css/adminMode.css";

</style>
