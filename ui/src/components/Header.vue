<template>
    <div>
        <h1 class="header">Logistics portal 2019</h1>
        <a href="#"><img class="logo" src="./img/logo.jpg"></a>
    </div>
</template>

<script>
    export default {
        name: "header"
    }
</script>

<style scoped>
    .header{
        background: rgba(117, 114, 114, 0.2);
        width: 100%;
        display: flex;
        justify-content: center;
        font-size: 34px;
        padding: 5px;
    }
    .logo{
        max-width: 100%;
        position: absolute;
        top: 5px;
        left: 10px;
    }
</style>