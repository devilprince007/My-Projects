function Hello(){
    let myName = 'Yash'
    let fullName = () =>{
        return 'Yash Patidar'
    }

    return <h2>Hello this is {myName} here. I am From Future {fullName()}</h2>
}

export default Hello