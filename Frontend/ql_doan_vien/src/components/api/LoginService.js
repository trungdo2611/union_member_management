import  axios  from "axios";

const API_URL = 'http://localhost:8080/auth';

export async function loginAdmin(values) {
    return await axios.post(`${API_URL}/logAdmin`,values);
}
