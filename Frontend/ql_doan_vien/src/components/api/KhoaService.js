import  axios  from "axios";

const API_URL = 'http://localhost:8080/khoa';

//Get khoa select
export async function getKhoaSelect() {
    return await axios.get(`${API_URL}/getKhoaSelect`);
}

// Thêm xóa sửa xem
export async function getKhoas(search, page = 0, size = 6) {
    return await axios.get(`${API_URL}/listKhoa?search=${search}&page=${page}&size=${size}`);
}

export async function detailKhoa(id) {
    return await axios.get(`${API_URL}/detailKhoa/${id}`);
}

export async function saveKhoa(khoa) {
    return await axios.post(`${API_URL}/create`, khoa);
}

export async function updateKhoa(khoa) {
    return await axios.put(`${API_URL}/edit`, khoa);
}

export async function deleteKhoa(id) {
    return await axios.delete(`${API_URL}/delete/${id}`);
}
