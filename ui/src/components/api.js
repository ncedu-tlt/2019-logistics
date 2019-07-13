import axios from 'axios'

const AXIOS = axios.create({
    baseURL: `http://localhost:8080/api/v1/logistics`,
    timeout: 10000
});


export default {
    findTowns() {
        return AXIOS.get(`/town`);
    },
}