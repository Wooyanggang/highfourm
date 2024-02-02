import React, { useState, useEffect } from 'react'

const Method = () => {
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch("/hi")
      .then(res => console.log(res))
      .then(message => setMessage(message))
      .catch(err => console.error(err));
  }, [])

  return (
    <div>
      <span>{message}</span>
    </div>
  )
}

export default Method

