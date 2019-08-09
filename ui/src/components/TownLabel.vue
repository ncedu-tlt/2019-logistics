<template>
    <div>
        <div class="info-field">
            <div class="town-search">
                <input class="town-search__input" type="text" name="townName" placeholder="Town name">
                <img class="town-search__lupa" src="./img/lupa.svg">
            </div>
            <div class="add-town">
                <div class="add-town__button">
                    Add town
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TownLabel"
    }
</script>

<style scoped>
    .info-field{
        display: table-row;
        width: 100%;
    }
    .town-search{
        display: table-cell;
        width: 100%;
    }
    .town-label{
        margin: 15px 0px 10px 20px;
    }
    .town-search__input{
        width: 142px;
        height: 22px;
        margin: 5px 10px 10px 18px;
        border-radius: 5px;
        border-width: 1px;
    }
    .town-search__lupa{
        margin-left: -5px;
        height: 15px;
        width: 15px;
    }
    .add-town{
        display: table-cell;
    }
    .add-town__button{
        background-color: #EAEAEA;
        border-radius: 10px;
        font-size: 15px;
        margin-right: 10px;
        padding: 2px;
        text-align: center;
        width: 80px;
        height: 22px;
    }
</style>