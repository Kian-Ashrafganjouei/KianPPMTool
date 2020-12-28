import React, { Component } from "react";
import CreateProjectButton from "./Project/CreateProjectButton";
import Projectitem from "./Project/Projectitem";
import Projectbutton from "./Project/CreateProjectButton";
class Dashboard extends Component {
  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center"> Projects </h1> <br />
              <div className="d-flex justify-content-center">
                <Projectbutton />
              </div>
              <br />
              <hr />
              <Projectitem />
            </div>{" "}
          </div>{" "}
        </div>{" "}
      </div>
    );
  }
}
export default Dashboard;
