import React from 'react'
import {TextField} from 'material-ui'



export class Login extends React.Component{

    constructor(){
        super()
    }


    render(){
        return <div>
            <Checkbox label="hello!"
                style={{
                width: '50%',
                margin: '0 auto',
                border: '2px solid #FF9800',
                backgroundColor: '#ffd699',
            }}/>
        </div>
    }
}