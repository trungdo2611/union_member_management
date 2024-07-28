import React from "react";
import './ListDoanVien.css'
import DoanVien from "../CardDoanVien/DoanVien";
const ListDoanVien = ({data, currentPage, getAllDoanViens, deleteDoanVien}) => {
  
    return (  
        <>
         <div className="doanvien__list">
                <div className="doanVien__contain">
                {data?.content?.length === 0 && <div>Không tìm thấy đoàn viên nào</div>}
                {data?.content?.length > 0 && data.content.map(doanvien => <DoanVien deleteDoanVien={deleteDoanVien} doanvien={doanvien} key={doanvien.id}/>)}
                </div>

                {data?.content?.length > 0 && data?.totalPages > 0 &&
                <div className="pagination">
              
                     <a onClick={() => getAllDoanViens(currentPage -1)} className={0 === currentPage ? 'disablePage' : ''}>&laquo;</a>
               
                                       
                    {data && [...Array(data.totalPages).keys()].map((page, index) => 
                    <a onClick={() => getAllDoanViens(page)} className={currentPage === page ? 'activePage' : ''} key={page}>{page +1}</a>)}

                   
                    <a onClick={() => getAllDoanViens(currentPage + 1)} className={data.totalPages === currentPage + 1 ? 'disablePage' : ''}>&raquo;</a>
                    
                </div>
                }
            </div>
        
        </>
           
    );
}
 
export default ListDoanVien;