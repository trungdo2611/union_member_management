import React from 'react'
import { UilSearch, UilPlus } from '@iconscout/react-unicons'
const HeaderKhoa = ({toogelModal, handleSearchKhoa, searchKhoa, setSearchKhoa}) => {
    const handleChangeKhoa = (event) => {
        setSearchKhoa(event.target.value);
      };
    
      const handleSubmitKhoa = (event) => {
        event.preventDefault();
        handleSearchKhoa(searchKhoa);
      };
  return (
    <div className="heading">
    <form onSubmit={handleSubmitKhoa} className="search">
      <label>
          <input id="search-input" 
                  value={searchKhoa}
                  onChange={handleChangeKhoa}                
                type="text" placeholder="Tìm ở đây..."/>
          <button  type="submit"> <UilSearch/></button>
      </label>
  </form>
  <button type="button" onClick={() => toogelModal(true)} className="btnAdd">
     <UilPlus/> <span>Thêm</span>
  </button>
  </div>
  )
}

export default HeaderKhoa