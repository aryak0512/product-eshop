<!DOCTYPE html>
<html>
<head>
    <title>Sample Form with reCAPTCHA</title>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <h1>Sample Form with reCAPTCHA</h1>
    <form action="login" method="post">
        <html:hidden property="token"/> <!-- This is to hold the reCAPTCHA response token -->

        <p>
            <label for="name">Name:</label>
            <html:text property="name" size="30" maxlength="50"/>
        </p>

        <!-- reCAPTCHA widget -->
        <div class="g-recaptcha" data-sitekey="6Le0htQpAAAAAJLQE2ol3FwWV-iXx1bwnvJpOeRX"></div>

        <input type="submit" value="SUBMIT"/ >
    </form>
</body>
</html>