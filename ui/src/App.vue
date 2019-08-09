<template>
    <div id="app">
        <Header></Header>
        <div id="nav">
            <router-link to="/">Towns</router-link>
        </div>
        <TownLabel></TownLabel>
        <router-view></router-view>
    </div>
</template>

<script>
    import Header from "./components/Header";
    import TownLabel from "./components/TownLabel";

    export default {
        name: 'app',
        components: {TownLabel, Header},
        data () {
            return {
                msg: 'Welcome to your Vue.js powered Spring Boot App'
            }
        }
    }
</script>

<style lang="scss">
    #app {
        font-family: 'Raleway', sans-serif;
        font-size: 20px;
        /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #2c3e50;
        /*margin-top: 20px;*/
    }
    #nav {
        /*padding: 10px;*/
        margin: 15px 0px 10px 20px;
        a {
            font-weight: bold;
            color: #2c3e50;
            &.router-link-exact-active {
                color: #42b983;
            }
        }
    }
    ul{
        margin: 0px;
        padding: 0px;
    }
</style>