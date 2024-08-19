import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddInvestigateForm.css';

function AddInvestigateForm() {
  const navigate = useNavigate();
  const { complaintId } = useParams(); // Fetch complaintId from URL
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [report, setReport] = useState('');

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  // Function to handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    const userData = JSON.parse(localStorage.getItem('user')); // Get userID from localStorage
    if (!userData || !userData.userId) {
      alert('User is not logged in or user ID is missing');
      return;
    }

    // Create data object to submit
    const data = {
      startDate: new Date(startDate).toISOString(), // Convert to ISO format
      endDate: new Date(endDate).toISOString(),     // Convert to ISO format
      report,
      authorityId: userData.userId,
      complaintId: parseInt(complaintId, 10), // Ensure complaintId is an integer
    };

    // Send the data to the backend
    fetch('http://localhost:8080/api/investigations', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then(response => response.json())
      .then(result => {
        alert('Investigation added successfully!');
        navigate('/my-investigations'); // Redirect after submission
      })
      .catch(error => console.error('Error adding investigation:', error));
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All New Complaints</a></li>
          <li><a href="/my-investigations">My Investigations</a></li>
        </ul>
      </div>
      
      <div className="content-area">
        <div className="top-nav">
          <input type="text" className="search-bar" placeholder="Search here..." />
          <div className="profile-options">
            <a href="/profile">Your Profile</a>
            <a href="/" onClick={handleLogout}>Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h1>Add Investigation Details</h1>
          <form className="investigation-form" onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="startDate">Start Date:</label>
              <input
                type="date"
                id="startDate"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="endDate">End Date:</label>
              <input
                type="date"
                id="endDate"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="report">Report:</label>
              <textarea
                id="report"
                placeholder="Enter report details"
                value={report}
                onChange={(e) => setReport(e.target.value)}
                required
              />
            </div>

            <button type="submit" className="submit-button">Add Investigation</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddInvestigateForm;
