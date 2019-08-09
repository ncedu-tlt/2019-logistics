<template>
    <div>
        <ul class="towns">
            <li v-for="town in towns.list" class="towns__town">
                <div class="towns__town-wrapper">
                    <div class="towns__town-description">
						<span class="towns__town-name">
							{{ town.name }}
						</span>
                        <span class="towns__town-toogle">
							<img src="./img/arrow.svg">
						</span>
                        <span class="towns__town-offices">
                            {{ msg }} offices
						</span>
<!--                        <span v-else class="towns__town-offices">-->
<!--                            No offices-->
<!--                        </span>-->
                    </div>
                    <div class="towns__town-menu">
                        <div class="menu">
							<span class="menu__button">
								Add office<br>
								Roads<br>
								Properties<br>
								Delete
							</span>
                        </div>
                    </div>
                </div>
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
                msg: 0,
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
            },
            check(townName) {
                api.countOffices(townName).then(response => {
                    this.msg = response.data
                })
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    li{
        list-style: none;
        padding: 10px;
        margin: 10px;
    }
    .towns{
        list-style-type: none;
        margin-left: 5px;
        padding-bottom: 5px;
    }
    .towns__town{
        border-radius: 10px;
        background-color: #F5F5F5;
    }
    .towns__town-wrapper{
        display: table-row;
        width: 100%;
    }
    .towns__town-description{
        display: table-cell;
        width: 100%;
    }
    .towns__town-toogle-rotated{
        width: 12px;
        height: 10px;
        -webkit-transform: rotate(180deg);
    }
    .towns__town-offices{
        color: #A9A9A9;
        font-size: 75%;
    }
    .towns__town-menu{
        display: table-cell;
    }
    div.menu{
        width: 30px;
        height: 20px;
        position: relative;
        background-image: url(./img/button.svg);
    }
    div.menu:hover{
        background-image: url(./img/button_hover.svg);
    }
    .menu__button{
        visibility: hidden;
        width: 80px;
        background-color: white;
        border-radius: 5px;
        border: 1px solid black;
        padding: 5px;
        font-size: 75%;

        position: absolute;
        z-index: 1;
        top: 100%;
        left: 20%;
        margin-left: -60px;
    }
    .menu:hover .menu__button{
        visibility: visible;
    }
</style>