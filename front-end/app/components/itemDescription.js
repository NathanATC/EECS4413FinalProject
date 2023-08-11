import React, {useState} from 'react';

const ItemDescriptionPopup = ({item, isVisible, onClose}) => {
    if(!isVisible) return null;
    return(
        <>
            <button onClick={()=>onClose()}>X</button>
            <div>   
                <p>{item.name}</p>
                <p>{item.description}</p>
            </div>
        </>
    );
}

export default ItemDescriptionPopup;