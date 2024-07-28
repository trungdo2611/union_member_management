import  axios  from "axios";

const API_URL = 'http://localhost:8080/sotay';

export async function getSoTayList(id) {
    return await axios.get(`${API_URL}/listSotay?idDV=${id}`);
}