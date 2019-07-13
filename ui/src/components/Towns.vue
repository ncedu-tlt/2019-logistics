<template>
    <div class="towns">
        Towns
        <ul id="jsTownsList">
            <li v-for="town in towns.list">
                {{ town.name }}
            </li>
        </ul>
    </div>
</template>

<script>
    import api from "./api";

    export default {
        name: 'towns',
        data () {
            return {
                towns: {
                    list: []
                }
            }
        },
        beforeMount() {
            this.findTowns()
        },
        methods: {
            findTowns() {
                api.findTowns().then(response => {
                        for (let town of response.data) {
                            this.towns.list.push(town)
                        }
                    })
                    .catch(e => {
                        this.errors.push(e)
                    })
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h1, h2 {
        font-weight: normal;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>