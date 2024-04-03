import { useRef } from "react";
import { useDispatch } from "react-redux";
import { counterActions } from "../store/counter";
import { privacyActions } from "../store/privacy";

const Controls = () => {
  const dispatch = useDispatch();
  const inputElement = useRef();

  const handleIncrement = () => {
    dispatch(counterActions.increment());
  };

  const handleDecrement = () => {
    dispatch(counterActions.decrement());
  };
  
  const handlePrivacy = () => {
    dispatch(privacyActions.toggle());
  };

  const handleAdd = () => {
    // dispatch(counterActions.add({
    //   num: inputElement.current.value,
    // }))

    dispatch(counterActions.add(inputElement.current.value))
    inputElement.current.value = "";
  };
  
  const handleSubstract = () => {
    // dispatch(counterActions.substract({
    //   num: inputElement.current.value,
    // }))
    
    dispatch(counterActions.substract(inputElement.current.value))
    inputElement.current.value = "";
  };

  return (
    <>
      <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
        <button
          type="button"
          className="btn btn-primary px-4 gap-3"
          onClick={handleIncrement}
        >
          +1
        </button>
        <button
          type="button"
          className="btn btn-success px-4"
          onClick={handleDecrement}
        >
          -1
        </button>
        <button
          type="button"
          className="btn btn-danger px-4"
          onClick={handlePrivacy}
        >
          Privacy Toggle
        </button>
      </div>

      <input
        type="text"
        placeholder="Enter Number"
        className="px-2 gap-4 control-row"
        ref={inputElement}
      />

      <div className="d-grid gap-2 d-sm-flex justify-content-sm-center control-row">
        <button
          type="button"
          className="btn btn-primary px-4 gap-3"
          onClick={handleAdd}
        >
          Add
        </button>
        <button
          type="button"
          className="btn btn-success px-4"
          onClick={handleSubstract}
        >
          Substract
        </button>
      </div>
    </>
  );
};

export default Controls;
