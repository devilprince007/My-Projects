
function App(){

  let foodItems = ['Dal','Green Veggies', 'Salad', 'Milk', 'Egg'];
  // let foodItems =[];

  // if(foodItems.length === 0){
  //   return <h1>i am still hungry</h1>
  // }

  // let emptyMessage = foodItems.length === 0 ? <h1>i am still hungry</h1> : null

  return (
    <>
      <h1>Healthy Food</h1>
      {/* {emptyMessage} */}
      {foodItems.length === 0 && <h2>i am still hungry</h2>}
      <ul className="list-group">
        {foodItems.map(item => <li key={item} className="list-group-item">{item}</li>)}
        
      </ul>
    </>
  )
}

export default App; 