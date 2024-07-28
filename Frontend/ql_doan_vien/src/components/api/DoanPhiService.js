import  axios  from "axios";

const API_URL = 'http://localhost:8080/doanphi';


//Thống kê
export async function totalDoanPhi() {
    return await axios.get(`${API_URL}/sumDoanPhi`);
}

export async function thongKeDoanPhi() {
    return await axios.get(`${API_URL}/thongkeDoanPhi`);
}

export async function countDoanVienInDoanPhi() {
    return await axios.get(`${API_URL}/countDVInDoanPhi`);
}


//thêm xóa sửa xem
export async function getDoanPhis(search,page = 0,size = 6) {
    return await axios.get(`${API_URL}/listDoanPhi?search=${search}&page=${page}&size=${size}`);
}

export async function detailDoanPhi(id) {
    return await axios.get(`${API_URL}/detailDoanPhi/${id}`);
}

export async function saveDoanPhi(doanphi) {
    return await axios.post(`${API_URL}/create`,doanphi);
}

export async function updateDoanPhi(doanphi) {
    return await axios.put(`${API_URL}/edit`,doanphi);
}

export async function deleteDoanPhi(id) {
    return await axios.delete(`${API_URL}/delete/${id}`);
}