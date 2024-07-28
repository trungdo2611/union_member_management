import  axios  from "axios";

const API_URL = 'http://localhost:8080/chidoan';


//Get chi đoàn select 
export async function getChiDoanSelectByKhoa(idKhoa) {
    return await axios.get(`${API_URL}/getChiDoanSelectByKhoa?khoa_id=${idKhoa}`);
}
export async function getChiDoanSelect() {
    return await axios.get(`${API_URL}/getChiDoanSelect`);
}


//Thêm xóa sửa xem
export async function getChiDoans(search,page = 0,size = 6) {
    return await axios.get(`${API_URL}/listChiDoan?search=${search}&page=${page}&size=${size}`);
}


export async function detailChiDoan(id) {
    return await axios.get(`${API_URL}/detailChiDoan/${id}`);
}

export async function saveChiDoan(chidoan) {
    return await axios.post(`${API_URL}/create`,chidoan);
}

export async function updateChiDoan(chidoan) {
    return await axios.put(`${API_URL}/edit`,chidoan);
}

export async function deleteChiDoan(id) {
    return await axios.delete(`${API_URL}/delete/${id}`);
}