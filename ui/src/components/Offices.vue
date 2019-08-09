<template>
    <ul class="offices">
        <li class="offices__office">
            <div class="offices__office-wrapper">
                <div class="offices__office-description">
                    <div class="offices__office-name-wrapper">
                        <div class="offices__office-name">
                            Cool Office
                        </div>
                    </div>
                    <div class="offices__office-phone-wrapper">
                        <div class="offices__office-data">
                            <span class="offices__office-phone-ico">
							    <img src="src/phone.svg">
							</span>
                            <span class="offices__office-phone">
							    895623
							</span>
                        </div>
                    </div>
                </div>
                <div class="offices__office-menu">
                    <div class="menu">
                        <span class="menu__button">
							Offerings<br>
							Properties<br>
							Delete
                        </span>
                    </div>
                </div>
            </div>
        </li>
        </ul>
        <span class="towns__more-button">Load more towns</span>
</template>

<script>
    import api from "./api"

    export default {
        name: "Offices",
        data() {
            return {
                offices: []
            }
        },
        beforeMount() {
            this.findOffices()
        },
        methods:{
            findOffices() {
                api.findOffices().then(response => {
                    for(let office of response.data){
                        this.offices.push(office)
                    }
                })
                    .catch(e => {
                        this.errors.push(e)
                    })
            }
        }
    }
</script>

<style scoped>
    .offices__office, .offices__no-office{
        border-radius: 10px;
        background-color: #FFFFFF;
    }
    .offices__no-office{
        margin: 10px;
        padding: 10px;
        text-align: center;
    }
    .offices__office-wrapper{
        display: table-row;
        width: 100%;
    }
    .offices__office-description{
        display: table-cell;
        width: 100%;
    }
    .offices__office-name-wrapper{
        display: table-cell;
        width: 100%;
    }
    .offices__office-name{
        width: 250px;
    }
    .offices__office-phone-wrapper{
        display: table-cell;
    }
    .offices__office-data{
        width: 120px;
        text-align: left;
    }
    .offices__office-phone-ico{
        margin-left: 40px;
    }
    .offices__office-menu{
        display: table-cell;
    }
    .offices__more-button{
        width: 180px;
        height: 20px;
        background-color: #D4D4D4;
        border-radius: 10px;
        padding: 0px 5px 5px 5px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }
</style>