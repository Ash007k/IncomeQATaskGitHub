FROM maven:3.8.2-jdk-8

LABEL maintainer="Ashok Kumar <Ashoktech.solutions@gmail.com>" \
      description="Image contains Maven Java and latest Chrome, Firefox and Edge browsers headless"

RUN apt-get update
RUN apt-get install -f -y fonts-liberation xdg-utils zip

# install chrome
RUN wget -O /usr/src/google-chrome-stable_current_amd64.deb "https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb" && \
  dpkg -i /usr/src/google-chrome-stable_current_amd64.deb ; \
  apt-get install -f -y && \
  rm -f /usr/src/google-chrome-stable_current_amd64.deb

# install firefox
RUN echo "deb http://deb.debian.org/debian/ unstable main contrib non-free" >> /etc/apt/sources.list.d/debian.list
RUN apt-get update
RUN apt-get install -y --no-install-recommends firefox
ENV MOZ_HEADLESS 1

# install edge
RUN curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg
RUN install -o root -g root -m 644 microsoft.gpg /etc/apt/trusted.gpg.d/
RUN sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/edge stable main" > /etc/apt/sources.list.d/microsoft-edge-dev.list'
RUN rm microsoft.gpg
RUN apt-get update
RUN apt-get install -y microsoft-edge-dev
RUN ln -s /usr/bin/microsoft-edge /usr/bin/edge

#versions
RUN google-chrome --version
RUN firefox --version
RUN edge --version
RUN java -version
RUN mvn --version

# Copy POM and cache dependency
WORKDIR /suite
COPY pom.xml /suite
RUN mvn install
CMD ["mvn"]
ENTRYPOINT ["/bin/bash", "-c"]
