'use client';
import {useState, useEffect} from 'react';
import ItemDescriptionPopup from './components/itemDescription';

const Home = () => {
  const [data, setData] = useState([]);
  const [itemData, setItemData] = useState({});
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    async function fetchData() {
      const res = await fetch("http://localhost:8080/Backend/catalogue");
      const fetchedData = await res.json()
      await setData(fetchedData);
    }
    fetchData();
  },[]);

  return ( 
  <>
    <div className="flex justify-center">
      <h1>Welcome!</h1>
    </div>
    <div className="flex justify-center">
      <h2>Hot Products!</h2>
    </div>
    <div className='flex flex-row gap-x-8 justify-center'>
    {
        data.map(
          (item) => {
            return(
              <>
              <div key={item.itemName}>
                <h1 className="flex justify-center"><b>{item.itemName}</b></h1>
                {item.imgSrc == "" ?
                  <div className='h-32 w-32'></div> : <img className="h-32 w-32" src={item.imgSrc} />}
                <p className="flex justify-center">{Intl.NumberFormat('en-US', {
                  style: 'currency',
                  currency: 'CAD',
                }).format(item.price)}</p>
                <div className="flex justify-center">
                  <button onClick ={() => {setItemData(item); setIsVisible(true);}}>More Info!</button>
                </div>
              </div>
              </>
            )
          }
        )
      }
    </div>
    <ItemDescriptionPopup item={itemData} isVisible={isVisible} onClose={()=>setIsVisible(false)}/>
  </>
  );
}

export default Home;