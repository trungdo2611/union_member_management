import React from 'react'
import { UilSearch, UilPlus } from '@iconscout/react-unicons'
const HearderChiDoi = ({toogelModal, searchChiDois, searchChiDoi, setSearchChiDoi}) => {
  const handleChangeChiDoi = (event) => {
    setSearchChiDoi(event.target.value);
  };

  const handleSubmitChiDoi = (event) => {
    event.preventDefault();
    searchChiDois(searchChiDoi);
  };
  return (
    <div className="heading">
    <form onSubmit={handleSubmitChiDoi} className="search">
      <label>
          <input id="search-input" 
                  value={searchChiDoi}
                  onChange={handleChangeChiDoi}                
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

export default HearderChiDoi