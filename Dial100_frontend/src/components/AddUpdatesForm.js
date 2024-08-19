import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddUpdatesForm.css';

function AddUpdatesForm() {
  const navigate = useNavigate();
  const { investigationId } = useParams(); // Get investigationId from URL params
  const [complaintId, setComplaintId] = useState(); // State to hold complaintId
  const [status, setStatus] = useState('OPEN');
  const [remarks, setRemarks] = useState('');
  const [updateDate, setUpdateDate] = useState('');
  
  // Parse user from localStorage
  const user = JSON.parse(localStorage.getItem('user')); // Parse user object

  // Fetch the complaintId based on investigationId
  useEffect(() => {
    fetch(`http://localhost:8080/api/investigations/getCompId/${investigationId}`)
      .then(response => response.json())
      .then(data => setComplaintId(data.complaintID)) // Ensure you set complaintId properly
      .catch(error => console.error('Error fetching complaint ID:', error));
  }, [investigationId]);

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Prepare payload
    const payload = {
      complaintId,
      authorityId: user ? user.userId : null, // Safeguard against null user
      status,
      remarks,
      updateDate
    };

    // Post payload to API
    fetch('http://localhost:8080/api/updates', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
      .then(response => response.json())
      .then(data => {
        console.log('Update successful:', data);
        navigate('/my-investigations'); // Redirect after success
      })
      .catch(error => console.error('Error updating:', error));
  };

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user'); // Remove user from localStorage
    navigate('/'); // Redirect to home
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All Complaints</a></li>
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
          <h1>Add Update Details:</h1>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Status:</label>
              <select value={status} onChange={(e) => setStatus(e.target.value)}>
                <option value="OPEN">OPEN</option>
                <option value="INPROGRESS">INPROGRESS</option>
                <option value="PENDING">PENDING</option>
                <option value="UNDER_REVIEW">UNDER_REVIEW</option>
                <option value="CLOSED">CLOSED</option>
                <option value="SUSPENDED">SUSPENDED</option>
                <option value="RESOLVED">RESOLVED</option>
                <option value="UNRESOLVED">UNRESOLVED</option>
              </select>
            </div>

            <div className="form-group">
              <label>Remarks:</label>
              <input
                type="text"
                value={remarks}
                onChange={(e) => setRemarks(e.target.value)}
                placeholder="Enter your remark"
              />
            </div>

            <div className="form-group">
              <label>Update Date:</label>
              <input
                type="date"
                value={updateDate}
                onChange={(e) => setUpdateDate(e.target.value)}
                placeholder="yyyy-mm-dd"
              />
            </div>

            <button type="submit" className="submit-btn">Add update</button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default AddUpdatesForm;
