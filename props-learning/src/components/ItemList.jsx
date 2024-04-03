import styles from "./ItemList.module.css"

const ItemList = ({ items, bought, handleBuyButton }) => {

  return (
    <li className= {`${styles["kg-item"]} list-group-item ${bought && 'active'}`}>
        <span className= {styles["kg-span"]}>{items}</span>
        <button 
          className={`${styles.button} btn btn-info`}
          onClick={handleBuyButton}
        >
          Buy
        </button>
    </li>
  )
}

export default ItemList