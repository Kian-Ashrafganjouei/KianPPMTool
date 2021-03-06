import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import classnames from "classnames";

class AddProject extends Component {
  constructor() {
    super();

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
      errors: {},
    };
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onChangeProjectName(e) {
    this.setState({
      projectName: e.target.value,
    });
  }

  onChangeProjectIdentifier(e) {
    this.setState({
      projectIdentifier: e.target.value,
    });
  }

  onChangeDescription(e) {
    this.setState({
      description: e.target.value,
    });
  }

  onChangeStart_Date(e) {
    this.setState({ start_date: e.target.value });
  }

  onChangeEnd_Date(e) {
    this.setState({
      end_date: e.target.value,
    });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    const { errors } = this.state;

    return (
      <div className="project">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">
                Tell Us About Your Project!{" "}
              </h5>{" "}
              <hr />
              <form
                onSubmit={this.onSubmit}
                onSubmit={this.onSubmit.bind(this)}
              >
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg ", {
                      "is-invalid": errors.projectName,
                    })}
                    placeholder="Project Name"
                    name="projectName"
                    value={this.state.projectName}
                    onChange={this.onChangeProjectName.bind(this)}
                  />{" "}
                  {errors.projectName && (
                    <div className="invalid-feedback">{errors.projectName}</div>
                  )}
                </div>{" "}
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg ", {
                      "is-invalid": errors.projectIdentifier,
                    })}
                    placeholder="Unique Project ID"
                    name="projectIdentifier"
                    value={this.state.projectIdentifier}
                    onChange={this.onChangeProjectIdentifier.bind(this)}
                  />{" "}
                  {errors.projectIdentifier && (
                    <div className="invalid-feedback">
                      {errors.projectIdentifier}
                    </div>
                  )}
                </div>{" "}
                <div className="form-group">
                  <textarea
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.description,
                    })}
                    placeholder="Project Description"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChangeDescription.bind(this)}
                  ></textarea>{" "}
                  {<div className="invalid-feedback">{errors.description}</div>}
                </div>{" "}
                <h6> Start Date </h6>{" "}
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="start_date"
                    value={this.state.start_date}
                    onChange={this.onChangeStart_Date.bind(this)}
                  />{" "}
                </div>{" "}
                <h6> Estimated End Date </h6>{" "}
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="end_date"
                    value={this.state.end_date}
                    onChange={this.onChangeEnd_Date.bind(this)}
                  />{" "}
                </div>
                <input
                  type="submit"
                  className="btn btn-success btn-block mt-4"
                />
              </form>{" "}
            </div>{" "}
          </div>{" "}
        </div>{" "}
      </div>
    );
  }
}

AddProject.propType = {
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createProject })(AddProject);
