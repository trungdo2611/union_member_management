import  axios  from "axios";

const API_URL = 'http://localhost:8080/doanvien';

//Thống kê
export async function totalDoanVien() {
    return await axios.get(`${API_URL}/countDoanVien`);
}

export async function thongKeDoanVien() {
    return await axios.get(`${API_URL}/thongkeDoanVien`);
}

//Get đoàn viên select
export async function getDoanVienSelectByChiDoan(id) {
    return await axios.get(`${API_URL}/getDoanVienSelectByChiDoan?chiDoan_id=${id}`);
}

export async function getDoanVienSelect() {
    return await axios.get(`${API_URL}/getDoanVienSelect`);
}


//Thêm xóa sửa xem
export async function saveDoanVien(doanVien) {
    return await axios.post(`${API_URL}/create`, doanVien);
}

export async function getDoanViens(search,page = 0, size = 6) {
    return await axios.get(`${API_URL}/listPagedoanvien?search=${search}&page=${page}&size=${size}`);
}

export async function detailDoanVien(id) {
    return await axios.get(`${API_URL}/detailDoanVien/${id}`);
}


export async function updateDoanVien(doanVien) {
    return await axios.put(`${API_URL}/edit`,doanVien);
}

export async function updatePhoto(formData) {
    return await axios.put(`${API_URL}/photo`,formData);
}

export async function deleteDoanVien(id) {
    return await axios.delete(`${API_URL}/delete/${id}`);
}
