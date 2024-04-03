import { useState } from 'react';
import './App.css'
import Container from './components/Container';
import ErrorMessage from "./components/ErrorMessage";
import FoodInput from './components/FoodInput';
import FoodItems from "./components/FoodItems";

function App() {
  // let foodItems = ['Dal', 'Egg', 'Milk', 'Green Vegetables', 'Salad'];
  // let foodItems = []

  // const[ textToShow, setTextToShow ] = useState();
  const [ foodItems, setFoodItems] = useState([]);
  
  const onKeyDown = (e) => {
    if(e.key === 'Enter'){
      let newFoodItem = e.target.value;
      e.target.value = '';
      let newItems = [...foodItems, newFoodItem]
      setFoodItems(newItems);
      console.log("food value entered is : "+newFoodItem)
    }
    console.log(e)
    // setTextToShow(e.target.value)
}
  return (
    <>
      <Container>
        <h1 className='food-heading'>Healthy Diets</h1>
        <FoodInput handleKeyDown={onKeyDown} />
        <ErrorMessage items={foodItems} />
        <FoodItems items={foodItems} />
      </Container>
    </>
  )
}

export default App
