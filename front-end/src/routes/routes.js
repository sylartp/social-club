import {BrowserRouter, Route} from 'react-router-dom'
import App from "../containers/App";


const Routes = (
    <BrowserRouter>
        <div>
            <Route exact path='/' component={App}/>
        </div>
    </BrowserRouter>
)

export default Routes;