import { useState } from "react"
import ItemList from "./ItemList"

const FoodItems = ({ items }) => {

  const [activeItems, setActiveItems] = useState([]);

  let OnBuyButton = (item, e) => {
    // console.log(`${item} bought`)
    // setActiveItems();
    let newItems = [...activeItems, item];
    setActiveItems(newItems);
  }
  
  return (
    <ul className='list-group'>
        {items.map((item) => <ItemList 
                                key={item} 
                                items={item} 
                                bought= {activeItems.includes(item)}
                                handleBuyButton={(e) => OnBuyButton(item, e)}
                              />
                  )
        }
      </ul>
  )
}

export default FoodItems