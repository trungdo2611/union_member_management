import React from 'react'
import { UilSearch, UilPlus } from '@iconscout/react-unicons';
const HeaderDoanPhi = ({toogelModal, handleSearchDoanPhi, searchDoanPhi, setSearchDoanPhi}) => {
    const handleChangeDoanPhi = (event) => {
        setSearchDoanPhi(event.target.value);
      };
    
      const handleSubmitDoanPhi = (event) => {
        event.preventDefault();
        handleSearchDoanPhi(searchDoanPhi);
      };
  return (
        <div className="heading">
        <form onSubmit={handleSubmitDoanPhi} className="search">
        <label>
            <input id="search-input" 
                    value={searchDoanPhi}
                    onChange={handleChangeDoanPhi}                
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

export default HeaderDoanPhi