import React from "react";
import "./Card.css"
import  PropTypes  from "prop-types";
const Card = ({cardInfo, onClick}) => {
    return ( 
        <div className="area-card"
        // onClick={onClick}
        style={{background: "linear-gradient(180deg, #FF919D 0%, #FC929D 100%)"}}
        >
            <div className="area-card-content">
                <div className="icon">
                    {cardInfo.icon}
                </div>
                <div className="area-card-info">
                    <h5 className="info-title"> {cardInfo.title}</h5>
                    <div className="info-value">{cardInfo.value}</div>
                </div>            
            </div>
            <div className="card-detail" onClick={onClick}>
                        {cardInfo.viewInfo}
                </div>
        </div>
     );
}
 
export default Card;

Card.propTypes = {
    cardInfo: PropTypes.object.isRequired,
};